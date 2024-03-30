package java.br.com.rafaellbarros.domain.model.entity;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.br.com.rafaellbarros.domain.core.model.entity.BaseEntity;
import java.time.LocalDate;
import java.util.List;


@ToString
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_user")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User extends BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "The field 'firstName' is mandatory")
    @Column(nullable = false)
    private String firstName;

    @NotNull(message = "The field 'lastName' is mandatory")
    @Column(nullable = false)
    private String lastName;

    @NotNull(message = "The field 'username' is mandatory")
    @Column(nullable = false)
    private String username;

    @NotNull(message = "The field 'email' is mandatory")
    @Column(nullable = false)
    private String email;

    @NotNull(message = "The field 'birthday' is mandatory")
    @Column(nullable = false)
    private LocalDate birthday;

    @NotNull(message = "The field 'login' is mandatory")
    @Column(nullable = false)
    private String login;

    @ToString.Exclude
    @NotNull(message = "The field 'password' is mandatory")
    @Column(nullable = false)
    private String password;

    @NotNull(message = "The field 'phone' is mandatory")
    @Column(nullable = false)
    private String phone;

    @NotNull(message = "The field 'role' is mandatory")
    @Column(nullable = false)
    @Builder.Default
    private String role = "USER";

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL,
            orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Car> cars;
    public User(@NotNull User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.role = user.getRole();
    }
}
