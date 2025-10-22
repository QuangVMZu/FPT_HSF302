package sum25.SE192755.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "SonyProducts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SonyProducts {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long productID;

    @Column(columnDefinition = "NVARCHAR(100)")
    private String productName;

    @Column
    private int price;

    @Column
    private int stock;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "cateID", referencedColumnName = "cateID")
    private SonyCategories sonyCategories;
}
