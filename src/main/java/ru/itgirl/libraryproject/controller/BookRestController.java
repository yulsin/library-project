package ru.itgirl.libraryproject.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import ru.itgirl.libraryproject.model.dto.BookCreateDto;
import ru.itgirl.libraryproject.model.dto.BookDto;
import ru.itgirl.libraryproject.model.dto.BookUpdateDto;
import ru.itgirl.libraryproject.service.BookService;

@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "library-users")
public class BookRestController {
    private final BookService bookService;

    @GetMapping("/book/{id}")
    BookDto getBookById(@PathVariable("id") Long id) {
        return bookService.getBookById(id);
    }

    @GetMapping("/book/v1")
    BookDto getBookByName(@RequestParam("name") String name) {
        return bookService.getBookByNameV1(name);
    }

    @GetMapping("/book/v2")
    BookDto getBookByNameV2(@RequestParam("name") String name) {
        return bookService.getBookByNameV2(name);
    }

    @GetMapping("/book/v3")
    BookDto getBookByNameV3(@RequestParam("name") String name) {
        return bookService.getBookByNameV3(name);
    }

    @PostMapping("/book/create")
    BookDto createBook(@RequestBody @Valid BookCreateDto bookCreateDto) throws Exception {
        return bookService.createBook(bookCreateDto);
    }

    @PutMapping("/book/update")
    BookDto updateBook(@RequestBody @Valid BookUpdateDto bookUpdateDto) {
        return bookService.updateBook(bookUpdateDto);
    }

    @DeleteMapping("/book/delete/{id}")
    void deleteBook(@PathVariable("id") Long id) {
        bookService.deleteBook(id);
    }
}
