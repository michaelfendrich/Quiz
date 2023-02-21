class QuizTemplate {

    async prepareTemplate(form) {
        const questions12 = [
            {
            "id": 1,
            "questionText": "Who was the first president of Czechoslovakia?",
            "answerA": "T.G.M.",
            "answerB": "Edvard Benes",
            "answerC": "Emil Hacha"
            },
            {
            "id": 2,
            "questionText": "What was the third biggest city in Czechoslovakia?",
            "answerA": "Brno",
            "answerB": "Bratislava",
            "answerC": "Ostrava"
            }
        ];
        const questions = await fetch('http://localhost:8080/questions').then(response => response.json());
        for (let i = 0; i < questions.length; i++) {
            let question = document.createElement("form");
            question.id = questions[i].id;
            question.innerHTML += `<h4 class="${i}">${questions[i].questionText}<h4>`;
            question.innerHTML += `<label class="neutral"><input type="radio" name="fav_language" value="a">${questions[i].answerA}<label><br>
                                    <label class="neutral"><input type="radio" name="fav_language" value="b">${questions[i].answerB}<label><br>
                                    <label class="neutral"><input type="radio" name="fav_language" value="c">${questions[i].answerC}<label><br>`;
            form.appendChild(question);
        }
    }
}