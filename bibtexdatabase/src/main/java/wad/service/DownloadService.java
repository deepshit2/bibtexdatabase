/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wad.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author santeri
 */
@Service
public class DownloadService {
    
    private Map<String, List<?>> listat;
    private Map<String, ServiceInterface> servicet;
    @Autowired
    private BookService bookService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private InproceedingsService inproceedingsService;

    @Autowired
    private MastersthesisService mastersthesisService;

    @Autowired
    private TechreportService techreportService;

    @Autowired
    private BookletService bookletService;

    @Autowired
    private ConferenceService conferenceService;

    @Autowired
    private InbookService inbookService;

    @Autowired
    private IncollectionService incollectionService;

    @Autowired
    private ManualService manualService;

    @Autowired
    private MiscService miscService;

    @Autowired
    private PhdthesisService phdthesisService;

    @Autowired
    private ProceedingsService proceedingsService;

    @Autowired
    private UnpublishedService unpublishedService;
    
    public String getBibtex(String searchtext) {
        initListat(searchtext);
        initServicet();
        return getAsBibtex(listat);
    }
    
    private String getAsBibtex(Map<String, List<?>> map) {
        String result = "";
        for (String name : map.keySet()) {
            result += getListAsBibtex(name, map.get(name));
        }
        return result;
    }

    private String getListAsBibtex(String name, List<?> list) {
        String result = "";
        if (list == null) {
            return result;
        }
        for (Object obj : list) {
            result += servicet.get(name).getBibtex(obj) + "\n\n";
        }
        return result;
    }

    private void initListat(String searchtext) {
        this.listat = new HashMap<>();
        addToLista(bookService.search(searchtext));
        addToLista(articleService.search(searchtext));
        addToLista(inproceedingsService.search(searchtext));
        addToLista(mastersthesisService.search(searchtext));
        addToLista(techreportService.search(searchtext));
        addToLista(bookletService.search(searchtext));
        addToLista(conferenceService.search(searchtext));
        addToLista(inbookService.search(searchtext));
        addToLista(incollectionService.search(searchtext));
        addToLista(manualService.search(searchtext));
        addToLista(miscService.search(searchtext));
        addToLista(phdthesisService.search(searchtext));
        addToLista(proceedingsService.search(searchtext));
        addToLista(unpublishedService.search(searchtext));
    }

    private void addToLista(List<?> list) {
        if (list != null && !list.isEmpty()) {
            String name = list.get(0).getClass().getSimpleName();
            this.listat.put(name, list);
        }
    }

    private void initServicet() {
        this.servicet = new HashMap<>();
        addToServicet(articleService);
        addToServicet(bookService);
        addToServicet(bookletService);
        addToServicet(conferenceService);
        addToServicet(inbookService);
        addToServicet(incollectionService);
        addToServicet(manualService);
        addToServicet(mastersthesisService);
        addToServicet(miscService);
        addToServicet(phdthesisService);
        addToServicet(proceedingsService);
        addToServicet(techreportService);
        addToServicet(unpublishedService);
        addToServicet(inproceedingsService);
    }

    private void addToServicet(ServiceInterface service) {
        List<?> list = service.list();
        if (list != null && !list.isEmpty()) {
            String name = list.get(0).getClass().getSimpleName();
            this.servicet.put(name, service);
        }
    }
}
