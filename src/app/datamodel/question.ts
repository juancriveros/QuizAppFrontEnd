import { Choices } from './choices';

export class Question {
    
    id : number;
    title : string;
    choices : Array<Choices>;
    examId : number;

    constructor(){
        
    }

}