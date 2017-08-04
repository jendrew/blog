package to.ogarne.ogarneblog.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import to.ogarne.ogarneblog.model.File;
import to.ogarne.ogarneblog.model.User;
import to.ogarne.ogarneblog.service.FileService;
import to.ogarne.ogarneblog.web.FlashMessage;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.Principal;

@Controller
public class FileController {

    @Autowired
    FileService fileService;

    @RequestMapping("/admin/addFile")
    public String fileUploadForm(Model model) {
        model.addAttribute("file", new File());
        return "/admin/file_upload";
    }


    @RequestMapping(value = "/admin/addFile", method = RequestMethod.POST)
    public String processFileUpload(@RequestParam("file") MultipartFile multipartFile,
                                    RedirectAttributes redirectAttributes,
                                    HttpServletRequest request,
                                    Principal principal) {

        File file = new File();
        try {
            file.setBytes(multipartFile.getBytes());
            file.setFilename(multipartFile.getOriginalFilename());
            file.setAuthor((User)((UsernamePasswordAuthenticationToken)principal).getPrincipal());
            file.setContentType(multipartFile.getContentType());
        } catch (IOException e) {
            e.printStackTrace();
        }

        fileService.save(file);

        redirectAttributes.addFlashAttribute("flash",
                new FlashMessage("You successfully uploaded " + file.getOriginalFilename() + "!",
                        FlashMessage.Status.SUCCESS));

        return "redirect:" + request.getServletPath();
    }


    // @RequestMapping("/files/{filename:.+}")
    // @ResponseBody
    // public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
    //
    //     Resource file = fileService.loadAsResource(filename);
    //     return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
    //             "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    // }

    @RequestMapping("/files/{id}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable Long id) {

        Resource file = fileService.loadAsResource(id);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }
}
