import { Component } from '@angular/core';
import { MapstedIonicPlugin } from 'src/plugins/plugins';
import { IonicModule } from '@ionic/angular';

@Component({
  selector: 'app-home',
  templateUrl: 'home.page.html',
  styleUrls: ['home.page.scss'],
  standalone: true,
  imports: [ IonicModule ],
})
export class HomePage {
  responseStr = '';
  responseArr = '';

  constructor() {

  }

  async testPlugin(){
    const { value } = await MapstedIonicPlugin.echo({ value: 'Hello World Calling from Ionic!' });
    this.responseStr = value;

    const { BuildingInfo } = await MapstedIonicPlugin.getBuildingInfo({ buildingId: 1 });
    this.responseArr = JSON.stringify(BuildingInfo);
  }
}
