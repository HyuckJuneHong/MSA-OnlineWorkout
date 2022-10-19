package kr.co.owocatalog.domain.entity;

import kr.co.owocatalog.domain.share.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_catalog")
@AttributeOverride(name = "id", column = @Column(name = "catalog_id"))
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CatalogEntity extends BaseEntity {

    @Column(name = "product_code", nullable = false, length = 120, unique = true)
    private String productCode;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "amount")
    private int amount;

    @Column(name = "price")
    private int price;

    @Builder
    public CatalogEntity(String productCode,
                         String productName,
                         int amount,
                         int price) {
        this.productCode = productCode;
        this.productName = productName;
        this.amount = amount;
        this.price = price;
    }
}
