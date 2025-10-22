package sum25.SE192755.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "SonyAccounts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SonyAccounts {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long accountID;

    @Column(columnDefinition = "NVARCHAR(13)")
    private String phone;

    @Column(columnDefinition = "NVARCHAR(10)")
    private String password;

    @Column
    private int roleId;
}
