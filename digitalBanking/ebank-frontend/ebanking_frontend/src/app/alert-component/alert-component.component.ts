import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-alert-component',
  templateUrl: './alert-component.component.html',
  styleUrls: ['./alert-component.component.css']
})



export class AlertComponentComponent implements OnInit {

  constructor() { }


  @Input() alertType: AlertType = AlertType.danger;
  @Input() alertMessage: String | undefined;

  alertTypeStr: String = AlertType[this.alertType];
  ngOnInit(): void {
  }

}

export enum AlertType {
  danger,
  primary,
  secondary ,
  success ,
  warning ,
  info
}
