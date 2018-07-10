public aspect AspectObserver {
    pointcut speakerSpeak():  
        call(void *Speaker.speak());  
    before() : speakerSpeak() {  
        System.out.println("[AspectObserver] speaker is about to speak!");  
    }  
    after() returning() : speakerSpeak() {  
        System.out.println("[AspectObserver] speaker has completed his speech!");  
    }  
}
