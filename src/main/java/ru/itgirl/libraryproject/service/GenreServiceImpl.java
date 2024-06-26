package ru.itgirl.libraryproject.service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.itgirl.libraryproject.model.dto.AuthorDto;
import ru.itgirl.libraryproject.model.dto.BookDto;
import ru.itgirl.libraryproject.model.dto.GenreDto;
import ru.itgirl.libraryproject.model.entity.Genre;
import ru.itgirl.libraryproject.repository.GenreRepository;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;

    @Override
    public GenreDto getGenreById(Long id) {
        log.info("Trying to find genre by ID: {}", id);
        Genre genre = genreRepository.findById(id).orElseThrow();
        log.info("Genre found: {}", genre);
        return convertToDto(genre);
    }

    private GenreDto convertToDto(Genre genre) {
        List<BookDto> bookDtoList = genre.getBooks()
                .stream()
                .map(book -> BookDto.builder()
                        .authors(book.getAuthors()
                                .stream()
                                .map(author -> AuthorDto.builder()
                                        .surname(author.getSurname())
                                        .name(author.getName())
                                        .id(author.getId())
                                        .build()
                                ).toList())
                        .name(book.getName())
                        .id(book.getId())
                        .build()
                ).toList();
        return GenreDto.builder()
                .books(bookDtoList)
                .id(genre.getId())
                .name(genre.getName())
                .build();
    }
}
