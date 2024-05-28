package jwtauth.springsecurityjwt.model;


import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class MetaData {

  @CreatedDate
  @Column(name = "created_date", updatable = false)
  private Date createdDate;

  @CreatedBy
  @Column(updatable = false)
  private String createdBy;

  @LastModifiedDate
  @Column(name = "updated_date", insertable = false)
  private Date updatedDate;
  @LastModifiedBy
  @Column(insertable = false)
  private String updatedBy;
}
