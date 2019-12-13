package hr.sedamit.demo.web.commands;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public class UpdateBookCommand {

    private String title;

    private Long authorId;
}
