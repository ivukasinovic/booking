import {Component, OnInit} from '@angular/core';
import {User} from '../models/user';
import {AuthService} from '../auth.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-add-agent',
  templateUrl: './add-agent.component.html',
  styleUrls: ['./add-agent.component.css']
})
export class AddAgentComponent implements OnInit {

  user: User;

  constructor(private authService: AuthService, private router: Router) {
    this.user = new User();
  }

  ngOnInit() {
  }

  register() {
    this.authService.registerAgent(this.user)
      .subscribe((data: User) => {
          alert('Succes registration ' + data.username + '!');
          this.router.navigate(['/admin']);
          window.location.reload();
        },
        error1 => {
          alert('Error!');
        }
      );
  }

}
