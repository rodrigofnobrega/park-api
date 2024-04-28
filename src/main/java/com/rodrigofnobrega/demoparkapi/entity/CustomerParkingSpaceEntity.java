package com.rodrigofnobrega.demoparkapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "clientes_tem_vagas")
@EntityListeners(AuditingEntityListener.class)
public class CustomerParkingSpaceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "numero_recibo", nullable = false, unique = true, length = 15)
    private String receipt;
    @Column(name = "placa", nullable = false, length = 8)
    private String carPlate;
    @Column(name = "marca", nullable = false, length = 45)
    private String carBrand;
    @Column(name = "modelo", nullable = false, length = 45)
    private String model;
    @Column(name = "color", nullable = false, length = 45)
    private String color;
    @Column(name = "data_entrada", nullable = false)
    private LocalDateTime entryDate;
    @Column(name = "data_saida")
    private LocalDateTime exitDate;
    @Column(name = "valor", columnDefinition = "decimal(7,2)")
    private BigDecimal value;
    @Column(name = "desconto", columnDefinition = "decimal(7,2)")
    private BigDecimal discount;

    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private CustomerEntity customerEntity;
    @ManyToOne
    @JoinColumn(name = "id_vaga", nullable = false)
    private ParkingSpaceEntity parkingSpaceEntity;

    @CreatedDate
    @Column(name = "date_created")
    private LocalDateTime dateCreated;
    @LastModifiedDate
    @Column(name = "date_modified")
    private LocalDateTime dateModified;
    @CreatedBy
    @Column(name = "created_by")
    private String createdBy;
    @LastModifiedBy
    @Column(name = "modified_by")
    private String modifiedBy;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerParkingSpaceEntity that = (CustomerParkingSpaceEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "CustomerParkingSpaceEntity{" +
                "id=" + id +
                ", receipt='" + receipt + '\'' +
                ", carPlate='" + carPlate + '\'' +
                ", carBrand='" + carBrand + '\'' +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", entryDate=" + entryDate +
                ", exitDate=" + exitDate +
                ", value=" + value +
                ", discount=" + discount +
                ", customerEntity=" + customerEntity +
                ", parkingSpaceEntity=" + parkingSpaceEntity +
                ", dateCreated=" + dateCreated +
                ", dateModified=" + dateModified +
                ", createdBy='" + createdBy + '\'' +
                ", modifiedBy='" + modifiedBy + '\'' +
                '}';
    }
}
