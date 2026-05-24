import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-register',
  imports: [FormsModule, RouterLink],
  templateUrl: './register.html',
})

export class Register {

  name = '';
  email = '';
  password = '';

  constructor(
    private auth: AuthService,
    private router: Router
  ) { }

  register() {

    const data = {
      name: this.name,
      email: this.email,
      password: this.password
    }

    this.auth.register(data).subscribe({
      next: () => {
        this.router.navigate(['/login']);
      }
    });

  } 

}
