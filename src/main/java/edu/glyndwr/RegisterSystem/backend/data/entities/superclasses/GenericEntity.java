
package edu.glyndwr.RegisterSystem.backend.data.entities.superclasses;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import lombok.Data;

/**
 *
 * @author Alexander Bruckbauer s17001620
 */
@Data
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class GenericEntity implements Serializable{
    @Id
    @GeneratedValue
    @Column(name="id")
    protected Long id;
}
