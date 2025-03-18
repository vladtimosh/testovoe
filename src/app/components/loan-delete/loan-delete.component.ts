import { Component, OnInit } from '@angular/core';
import { LoanService } from '../../services/loan.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-loan-delete',
  standalone: true,
  imports: [],
  templateUrl: './loan-delete.component.html',
  styleUrls: ['./loan-delete.component.css'],
})
export class LoanDeleteComponent implements OnInit {
  constructor(
    private loanService: LoanService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.deleteLoan(+id);
    }
  }

  deleteLoan(id: number): void {
    this.loanService.deleteLoan(id).subscribe(() => {
      this.router.navigate(['/loans']);
    });
  }
}
