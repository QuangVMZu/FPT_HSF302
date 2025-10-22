package sum25.SE192755.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "SonyCategories")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SonyCategories {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long cateID;

    @Column(columnDefinition = "NVARCHAR(50)")
    private String cateName;

    @Column(columnDefinition = "NVARCHAR(10)")
    private String status;

    @OneToMany(mappedBy = "sonyCategories", cascade = CascadeType.ALL)
    private List<SonyProducts> sonyProducts;
}
