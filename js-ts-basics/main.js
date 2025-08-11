//Масиви 
var numbers = [1, 2, 3, 4];
var names = ['Anna', 'Bob', 'Valitos'];
var user1 = {
    name: 'Valitos',
    age: 25
};
//Фінкція з типами параметрів і повернення
function greetUser(user) {
    return "\u041F\u0440\u0438\u0432\u0456\u0442 ".concat(user.name, "! \u0422\u043E\u0431\u0456 ").concat(user.age, " \u0440\u043E\u043A\u0456\u0432.");
}
// оголошення змінних
var age = 20;
var userName = "Valitos";
var isStudent = true;
// Функція вітання
function greet(name) {
    return "\u041F\u0440\u0438\u0432\u0456\u0442 ".concat(name, "!");
}
console.log(greet(userName));
console.log(greetUser(user1));
