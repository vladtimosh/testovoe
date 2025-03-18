import { Component, OnInit } from '@angular/core';
import { LoanService } from '../../services/loan.service';
import { Loan } from '../../models/loan';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-loan-form',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './loan-form.component.html',
  styleUrls: ['./loan-form.component.css'],
})
export class LoanFormComponent implements OnInit {
  loan: any = {
    // loanId: 0,
    book: { bookId: 0, title: '', publishedDate: new Date(), genre: { genreId: 0, genreName: '' }, authors: [] },
    member: { memberId: 0, fullName: '', membershipDate: new Date() },
    loanDate: new Date(),
    returnDate: new Date(),
  };
  isEditMode = false;

  constructor(
    private loanService: LoanService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.isEditMode = true;
      this.getLoanById(+id);
    }
  }

  getLoanById(id: number): void {
    this.loanService.getLoanById(id).subscribe((data) => {
      this.loan = data;
    });
  }

  onSubmit(): void {
    if (this.isEditMode) {
      this.loanService
        .updateLoan(this.loan.loanId, this.loan)
        .subscribe(() => {
          this.router.navigate(['/loans']);
        });
    } else {
      this.loanService.createLoan(this.loan).subscribe(() => {
        this.router.navigate(['/loans']);
      });
    }
  }
}
