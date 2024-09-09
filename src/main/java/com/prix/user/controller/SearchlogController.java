package com.prix.user.controller;

import com.prix.user.service.SearchlogService;
import com.prix.user.DTO.SearchlogDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class SearchlogController {
    private final SearchlogService searchlogService;

    @PostMapping("/actgprocess")
    public String actgPost(SearchlogDTO searchlogDTO) {
        searchlogService.saveSearchlog(searchlogDTO.toEntity());
        return "redirect:/";
    }

    @GetMapping("/admin/searchlog")
    public String searchlogList(Model model) {
        List<SearchlogDTO> searchlogDTOList = searchlogService.getSearchlogList();
        model.addAttribute("searchlogList", searchlogDTOList);
        return "admin/searchlog";
    }

    @GetMapping("/livesearch/history")
    public String history(Principal principal, Model model) {
        String userID = (principal != null) ? principal.getName() : "anonymous";
        List<SearchlogDTO> searchlogDTOList = searchlogService.findByUserID(userID);
        model.addAttribute("searchlogList", searchlogDTOList);
        return "/livesearch/history";
    }
}
