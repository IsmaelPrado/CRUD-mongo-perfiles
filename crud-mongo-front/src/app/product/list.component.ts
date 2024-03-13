import { Component, OnInit } from '@angular/core';
import { ProductService } from '../services/product.service';
import { ToastrService } from 'ngx-toastr';
import { Product } from '../model/product';
import Swal from 'sweetalert2';
import { StorageService } from '../services/storage.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css']
})
export class ListComponent implements OnInit {

  products: Product[] = [];

  constructor(
    private productService: ProductService,
    private toast: ToastrService,
    private storageService: StorageService,
    private route: Router
  ) { }

  ngOnInit(): void {
    this.getProducts();
  }

  getProducts(): void{
    this.productService.list().subscribe(
      data => {
        this.products = data;
        console.log(this.products);
      },
      err => {
        this.toast.error(err.error.message, 'Error', {timeOut: 3000, positionClass: 'toast-top-center'})
      }
    )
  }

  onDelete(id:number): void{
    Swal.fire({
      title: 'Are you sure?',
      text: 'You cannot undo',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'OK',
      cancelButtonText: 'Cancel'
    }).then((result) => {
      if(result.value){
        console.log('Deleted product' + id);
        this.productService.delete(id).subscribe(
          data => {
            this.toast.success(data.message, 'OK', {timeOut: 3000, positionClass: 'toast-top-center'});
            this.getProducts();
          },
          err => {
            this.toast.error(err.error.message, 'Error', {timeOut: 3000, positionClass: 'toast-top-center'});
          }
        )
      } else if(result.dismiss === Swal.DismissReason.cancel){
        Swal.fire(
          'Canceled',
          'Product not deleted',
          'error'
        )
      }
    })
  }

  setProduct(product: Product){
    this.storageService.setProduct(product);
    this.route.navigate(['/update']);
  }

}
