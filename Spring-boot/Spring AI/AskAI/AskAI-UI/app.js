/* app.js
   - Flexible client for ASK AI Spring Boot backend running on port 8081.
   - If your backend endpoint differs, change API_URL below.
   - The client will attempt to send both { message } and { prompt } to be compatible
     with common example backends; the parser accepts several response shapes.
*/

const API_URL = 'http://localhost:8081/api/chat'; // <-- update if your Spring boot endpoint is /ask or /v1/ask etc.
const messagesEl = document.getElementById('messages');
const form = document.getElementById('chatForm');
const textInput = document.getElementById('textInput');
const clearBtn = document.getElementById('clearBtn');
const reloadBtn = document.getElementById('reloadBtn');

function createMessageEl(text, who = 'bot', opts = {}) {
  const wrapper = document.createElement('div');
  wrapper.className = `msg ${who}`;
  const avatar = document.createElement('div');
  avatar.className = `avatar ${who}`;
  avatar.textContent = who === 'bot' ? 'A' : 'YOU';
  const bubble = document.createElement('div');
  bubble.className = 'bubble';
  bubble.innerHTML = text;
  wrapper.appendChild(avatar);
  wrapper.appendChild(bubble);
  return wrapper;
}

function appendBotText(text) {
  const el = createMessageEl(escapeHtml(text), 'bot');
  messagesEl.appendChild(el);
  scrollToBottom();
}

function appendUserText(text) {
  const el = createMessageEl(escapeHtml(text), 'user');
  messagesEl.appendChild(el);
  scrollToBottom();
}

function scrollToBottom() {
  messagesEl.scrollTop = messagesEl.scrollHeight;
}

function addTyping() {
  const el = document.createElement('div');
  el.className = 'msg bot';
  el.id = 'typing';
  el.innerHTML = `<div class="avatar bot">A</div><div class="bubble"><span class="typing"><span></span><span></span><span></span></span></div>`;
  messagesEl.appendChild(el);
  scrollToBottom();
}

function removeTyping() {
  const e = document.getElementById('typing');
  if (e) e.remove();
}

/* Very small sanitizer for simple text (keeps basic formatting) */
function escapeHtml(unsafe) {
  if (unsafe == null) return '';
  return ('' + unsafe)
    .replaceAll('&', '&amp;')
    .replaceAll('<', '&lt;')
    .replaceAll('>', '&gt;')
    .replaceAll('\n', '<br/>');
}

/* Attempt to parse multiple response shapes common in LLM APIs */
function extractReply(obj) {
  if (!obj) return null;
  // If backend returns a direct string
  if (typeof obj === 'string') return obj;
  // common: { reply: '...' }
  if (obj.reply) return obj.reply;
  // common: { message: '...' }
  if (obj.message) return obj.message;
  // common: { choices: [ { text: '...' } ] }
  if (obj.choices && Array.isArray(obj.choices) && obj.choices[0]?.text) return obj.choices[0].text;
  // some spring-ai examples return content in messages array
  if (obj.messages && Array.isArray(obj.messages) && obj.messages[0]) return extractReply(obj.messages[0]);
  // unknown fallback: JSON stringify
  return JSON.stringify(obj);
}

async function queryBackend(prompt) {
  // Try to send a flexible payload. Some backends expect { message }, others { prompt }.
  const payloads = [
    { message: prompt },
    { prompt },
    { input: prompt }
  ];

  // We'll attempt each payload in order until one returns success
  let lastErr = null;
  for (const payload of payloads) {
    try {
      const res = await fetch(API_URL, {
        method: 'POST',
        mode: 'cors',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(payload),
      });

      if (!res.ok) {
        lastErr = new Error(`HTTP ${res.status}`);
        // Continue to next payload shape; but if 4xx/5xx on same endpoint likely server issue.
        // We'll still try to parse body for error details.
      }

      // Try to parse the response as JSON, fallback to text
      let bodyText = await res.text();
      try {
        const json = JSON.parse(bodyText);
        const reply = extractReply(json);
        return reply;
      } catch (e) {
        // maybe plain text
        if (bodyText && bodyText.trim()) return bodyText;
      }
    } catch (err) {
      lastErr = err;
      // network error — try next payload shape (unlikely to help) then fallback to throwing
    }
  }

  throw lastErr || new Error('No response from server');
}

/* UI handlers */
form.addEventListener('submit', async (e) => {
  e.preventDefault();
  const text = textInput.value.trim();
  if (!text) return;
  appendUserText(text);
  textInput.value = '';
  addTyping();

  try {
    const reply = await queryBackend(text);
    removeTyping();
    appendBotText(reply ?? 'Sorry — no answer received.');
  } catch (err) {
    removeTyping();
    appendBotText(`Error: ${err?.message || 'Failed to contact backend'}`);
    console.error('ASK AI request error', err);
  }
});

clearBtn.addEventListener('click', () => {
  messagesEl.innerHTML = '';
  appendBotText("Hi — I'm ASK AI. Ask me anything!");
});

reloadBtn.addEventListener('click', () => {
  window.location.reload();
});

/* initial greeting */
window.addEventListener('load', () => {
  messagesEl.innerHTML = '';
  appendBotText("Hi — I'm ASK AI. Ask me anything!");
});
