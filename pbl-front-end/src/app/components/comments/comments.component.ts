import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-comments',
  templateUrl: './comments.component.html',
  styleUrls: ['./comments.component.css']
})
export class CommentsComponent implements OnInit {
  @Input()
  data:[];

  // data = [
  //   {
  //     id:'1',
  //     author: 'Han Solo',
  //     reply: '',
  //     submitting : false,
  //     content:
  //       'We supply a series of design principles, practical patterns and high quality design resources' +
  //       '(Sketch and Axure), to help people create their product prototypes beautifully and efficiently.',
  //     datetime: '2020-06-10-20:00',
  //     children: [
  //       {
  //         id:'2',
  //         author: 'Han Solo',
  //         reply:'',
  //         submitting : false,
  //         content:
  //           'We supply a series of design principles, practical patterns and high quality design resources' +
  //           '(Sketch and Axure), to help people create their product prototypes beautifully and efficiently.',
  //         children: [
  //           {
  //             id:'3',
  //             author: 'Han Solo',
  //             reply:'',
  //             submitting : false,
  //             content:
  //               'We supply a series of design principles, practical patterns and high quality design resources' +
  //               '(Sketch and Axure), to help people create their product prototypes beautifully and efficiently.',
  //             children:[]
  //           },
  //           {
  //             id:'4',
  //             author: 'Han Solo',
  //             reply:'',
  //             submitting : false,
  //             content:
  //               'We supply a series of design principles, practical patterns and high quality design resources' +
  //               '(Sketch and Axure), to help people create their product prototypes beautifully and efficiently.',
  //             children:[]
  //           }
  //         ]
  //       },
  //       {
  //         id:'5',
  //         author: 'Han Solo',
  //         reply:'',
  //         submitting : false,
  //         content:
  //           'We supply a series of design principles, practical patterns and high quality design resources' +
  //           '(Sketch and Axure), to help people create their product prototypes beautifully and efficiently.',
  //         children:[]
  //       }
  //     ]
  //   },
  //   {
  //     author: 'Han Solo',
  //     reply:'',
  //     submitting : false,
  //     content:
  //       'We supply a series of design principles, practical patterns and high quality design resources' +
  //       '(Sketch and Axure), to help people create their product prototypes beautifully and efficiently.',
  //     children:[]
  //   }
  // ];

  constructor() { }

  ngOnInit(): void {
  }

  changeReply(comment:any):void{
    comment.reply = !comment.reply;
  }

  handleSubmit(comment:any): void {
    const content = comment.reply;
    comment.submitting=true;
    comment.reply = '';
    setTimeout(() => {
      comment.submitting=false;
      comment.children.push({
        author: '林晓青',
        reply:'',
        submitting:false,
        content:content
      });
    }, 800);
  }
}
