package com.mrm.Resume.Analyzer;

import org.apache.tika.Tika;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/api/resume")
@CrossOrigin("*")
public class ResumeController {

    private final ChatClient chatClient;

    private final Tika tika;

    public ResumeController(OpenAiChatModel openAiChatModel){
        this.chatClient = ChatClient.create(openAiChatModel);
        this.tika = new Tika();
    }

    @PostMapping("/analyser")
    public Map<String, Object> analyser(@RequestParam("file") MultipartFile file) throws Exception{

        String content = tika.parseToString(file.getInputStream());

        String prompt = """
                
                Analyse this resume text:
                %s
                1. Extract key skills
                2. Rate overall resume quality in 1 to 10
                3. Suggest 3 improvements.
                Reply in structured json format.
                """.formatted(content);

        String aiResponse = chatClient.prompt()
                .user(prompt)
                .call()
                .content();

        System.out.println("response : "+aiResponse);
        return Map.of("analysis",aiResponse);
    }


    @PostMapping("/ats-checker")
    public Map<String, Object> ats_checker(@RequestParam("file") MultipartFile file,
                                           @RequestParam("jd") String jobDescription) throws Exception{
        String resumeText = tika.parseToString(file.getInputStream());

        String prompt =
                """
                You are an expert ATS analyzer. Compare the resume and job description.
                
                ----
                %s
                ----
                Job description :
                
                %s
                ------
                
                Analyse and returned structured json with:
                1. "ats-score" (1-100)
                2. "match keywords" (List)
                3. "missing keywords" (List)
                4. "summary" (Short paragraph)
                
                keep response strictly in valid json format.
                """.formatted(resumeText, jobDescription);

        String aiResponse = chatClient.prompt()
                .user(prompt)
                .call()
                .content();

        return Map.of("ATS-Report",aiResponse);

    }
}
