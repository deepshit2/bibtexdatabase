package wad.controller;

import java.io.IOException;
import java.io.InputStream;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.RequestMethod;
import wad.service.DownloadService;

@Controller
@RequestMapping(value = "/download")
public class DownloadController {
    
    @Autowired
    DownloadService downloadService;

    @RequestMapping(method = RequestMethod.GET)
    public String download(@ModelAttribute("searchtext") String searchtext, HttpServletResponse response) {
        String bibtex = downloadService.getBibtex(searchtext);
        try {
            InputStream input = IOUtils.toInputStream(bibtex, "UTF-8");
            IOUtils.copy(input, response.getOutputStream());
            response.flushBuffer();
        } catch (IOException ex) {
            throw new RuntimeException("IOError writing file to output stream");
        }
        return "index";
    }

}
