package to.ogarne.ogarneblog.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;
import to.ogarne.ogarneblog.model.sitemap.ChangeFrequency;
import to.ogarne.ogarneblog.model.sitemap.Sitemap;
import to.ogarne.ogarneblog.model.sitemap.SitemapURL;
import to.ogarne.ogarneblog.service.PageService;
import to.ogarne.ogarneblog.service.PostService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;


@Controller
public class SitemapController {


    @Value("${sitemap.base.url}")
    String baseUrl;

    @Value("${sitemap.change.frequency}")
    String changeFrequency;

    @Value("${sitemap.priority}")
    Float priority;

    @Autowired
    PostService postService;

    @Autowired
    PageService pageService;


    @ResponseBody
    @RequestMapping(value = "/sitemap.xml", produces = "application/xml")
    public Sitemap getSitemap(HttpServletRequest request) {

        final UriComponentsBuilder uriBuilder = ServletUriComponentsBuilder.fromRequest(request).replacePath(null);


        List<SitemapURL> postUrls = postService.findAll().stream()
                .map(post -> {
                    SitemapURL url = new SitemapURL();
                    url.setLocation(baseUrl + "/posts/" + post.getSlug());
                    url.setChangeFrequency(ChangeFrequency.hourly);
                    url.setLastModified(post.getDateModified());
                    url.setPriority(priority);
                    return url;
                })
                .collect(Collectors.toList());

        List<SitemapURL> pageUrls = pageService.findAll().stream()
                .map(page -> {
                    SitemapURL url = new SitemapURL();
                    url.setLocation(baseUrl + "/pages/" + page.getSlug());
                    url.setChangeFrequency(ChangeFrequency.hourly);
                    url.setLastModified(page.getDateModified());
                    url.setPriority(priority);
                    return url;
                })
                .collect(Collectors.toList());

        postUrls.addAll(pageUrls);
        return new Sitemap(postUrls);

    }



}

