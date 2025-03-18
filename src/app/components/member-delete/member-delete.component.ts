import { Component, OnInit } from '@angular/core';
import { MemberService } from '../../services/member.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-member-delete',
  standalone: true,
  imports: [],
  templateUrl: './member-delete.component.html',
  styleUrls: ['./member-delete.component.css'],
})
export class MemberDeleteComponent implements OnInit {
  constructor(
    private memberService: MemberService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.deleteMember(+id);
    }
  }

  deleteMember(id: number): void {
    this.memberService.deleteMember(id).subscribe(() => {
      this.router.navigate(['/members']);
    });
  }
}
