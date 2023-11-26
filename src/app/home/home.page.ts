import { Component } from '@angular/core';
import { IonHeader, IonToolbar, IonTitle, IonContent } from '@ionic/angular/standalone';
import { MapstedIonicPlugin } from 'src/plugins/plugins';


@Component({
  selector: 'app-home',
  templateUrl: 'home.page.html',
  styleUrls: ['home.page.scss'],
  standalone: true,
  imports: [IonHeader, IonToolbar, IonTitle, IonContent],
})
export class HomePage {
  constructor() {

  }

  async testPlugin(){
    const { value } = await MapstedIonicPlugin.echo({ value: 'Hello World!' });
    console.log('Response from native:', value);
  }
}
