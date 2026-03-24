package it.accenture.library.entity;

import it.accenture.library.entity.embeddedId.LoanPk;
import it.accenture.library.to.LoanTO;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "LOAN")
public class Loan {

    @EmbeddedId
    private LoanPk id;

    @Column(name = "start_date")
    private Long startDate;

    @Column(name = "end_date")
    private Long endDate;

    public Loan(LoanTO loanTO) {
        this.id = new LoanPk(loanTO.getUserId(), loanTO.getBookId());
        this.startDate = loanTO.getStartDate();
        this.endDate = loanTO.getEndDate();
    }
}
