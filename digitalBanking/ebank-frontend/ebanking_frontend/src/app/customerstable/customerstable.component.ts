import { HttpClient } from '@angular/common/http';
import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-customerstable',
  templateUrl: './customerstable.component.html',
  styleUrls: ['./customerstable.component.css']
})
export class CustomerstableComponent implements OnInit {
  @Input() customersList: any; // get data from parent
  constructor() { }

  ngOnInit(): void {

  }

}
