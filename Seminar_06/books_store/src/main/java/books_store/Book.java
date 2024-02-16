package books_store;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column
    private String author;
    @ManyToOne // Говорит что здесь линкуется список, т.е. что у reader может быть несколько книжек
    @JoinColumn(name = "reader_id") // объявляем виртуальное поле, по сути это foreign key - внешний ключ
    // который связан с ключом id таблицы Reader
    private Reader reader;
}
