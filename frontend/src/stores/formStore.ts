import { defineStore } from "pinia";

export const userFormStore = defineStore('form',{
    state: () => ({
        name: '',
        email: '',
        message: ''
    }),
    actions:{
        setName(name: string){
            this.name = name;
        },
        setEmail(email: string){
            this.email = email;
        },
        setMessage(message: string){
            this.message = message;
        },
        resetForm(){
            this.name = '';
            this.email = '';
            this.message = '';
        }

    }
})