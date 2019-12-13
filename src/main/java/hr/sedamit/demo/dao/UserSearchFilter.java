package hr.sedamit.demo.dao;

import lombok.Getter;

@Getter
public class UserSearchFilter {

    private Boolean active;

    private Integer olderThenAge;

    public UserSearchFilter(Boolean active, Integer olderThenAge) {
        this.active = active;
        this.olderThenAge = olderThenAge;
    }
}
