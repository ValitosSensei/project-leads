//Масиви 
let numbers: number[] = [1, 2, 3, 4];
let names: Array<string> = ['Anna', 'Bob', 'Valitos']

//Обєкти з інтерфейсами 
interface User{
    name:string;
    age: number;
    isStident?: boolean;
}

const user1: User = {
    name:'Valitos',
    age: 25
};

//Фінкція з типами параметрів і повернення
function greetUser(user:User): string{
    return `Привіт ${user.name}! Тобі ${user.age} років.`;
}


// оголошення змінних
let age: number = 20;
const userName: string = "Valitos";
let isStudent: boolean = true;

// Функція вітання

function greet(name: string): string{
    return `Привіт ${name}!`;
}

console.log(greet(userName));
console.log(greetUser(user1));

