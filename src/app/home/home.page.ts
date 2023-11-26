import { Component } from '@angular/core';
import { MapstedIonicPlugin } from 'src/plugins/plugins';
import { IonItem, IonButton, IonContent, IonHeader, IonToolbar, IonTitle, IonLabel, IonInput } from '@ionic/angular/standalone';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-home',
  templateUrl: 'home.page.html',
  styleUrls: ['home.page.scss'],
  standalone: true,
  imports: [IonItem, IonButton, IonContent, IonHeader, IonToolbar, IonTitle, IonLabel, IonInput, FormsModule]
})
export class HomePage {
  buildingId = 504;
  responseStr = '';
  responseArr = '';

  constructor() {

  }

  async testPlugin() {
    try {
      const { value } = await MapstedIonicPlugin.echo({ value: 'Hello World Calling from Ionic!' });
      this.responseStr = value;

      const { BuildingInfo } = await MapstedIonicPlugin.getBuildingInfo({ buildingId: parseInt(this.buildingId.toString()) });
      this.responseArr = JSON.stringify(BuildingInfo);
    } catch (error) {
      alert(error)
    }
  }
}
