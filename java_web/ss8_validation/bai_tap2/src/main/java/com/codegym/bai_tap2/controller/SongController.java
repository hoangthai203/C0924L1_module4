package com.codegym.bai_tap2.controller;

import com.codegym.bai_tap2.model.Song;
import com.codegym.bai_tap2.service.ISongService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/songs")
public class SongController {

    @Autowired
    private ISongService songService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("songs", songService.findAll());
        return "song/list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("song", new Song());
        return "song/form";
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute Song song, BindingResult br, RedirectAttributes ra) {
        if (br.hasErrors()) {
            return "song/form";
        }
        songService.save(song);
        ra.addFlashAttribute("success", "Thêm bài hát thành công");
        return "redirect:/songs";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        Song song = songService.findById(id).orElseThrow();
        model.addAttribute("song", song);
        return "song/form";
    }

    @PostMapping("/{id}/edit")
    public String update(@Valid @ModelAttribute Song song, BindingResult br, RedirectAttributes ra) {
        if (br.hasErrors()) {
            return "song/form";
        }
        songService.update(song);
        ra.addFlashAttribute("success", "Cập nhật thành công");
        return "redirect:/songs";
    }
}
