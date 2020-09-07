package com.zh.controller;

import com.zh.entity.mongo.Note;
import com.zh.entity.mongo.NoteAddDTO;
import com.zh.entity.mongo.NoteUpdateDTO;
import com.zh.service.mongo.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/note")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @GetMapping("/find/{id}")
    public Note findById(@PathVariable String id) {
        return noteService.findById(id);
    }

    @GetMapping("/listAll")
    public List<Note> listAll() {
        return noteService.listAll();
    }

    @PostMapping("/add")
    public String add(@RequestBody NoteAddDTO note) {
        return noteService.add(note.getContent(), note.getImportant());
    }

    @PostMapping("/update")
    public Long update(@RequestBody NoteUpdateDTO noteUpdate) {
        return noteService.update(noteUpdate);
    }

    @GetMapping("/remove/{id}")
    public Long remove(@PathVariable("id") String id) {
        return noteService.remove(id);
    }
}
