// script.js
document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('dynamic-form');
    let questionCount = 2;

    // 질문 추가 버튼 클릭 이벤트
    const addQuestionButton = document.createElement('button');
    addQuestionButton.innerHTML = '질문 추가';
    addQuestionButton.style.marginTop = '20px';
    addQuestionButton.style.width = '100%';
    addQuestionButton.style.backgroundColor = '#f0f0f0';
    addQuestionButton.style.color = '#333';
    addQuestionButton.style.border = 'none';
    addQuestionButton.style.padding = '12px';
    addQuestionButton.style.cursor = 'pointer';
    addQuestionButton.addEventListener('click', function() {
        if (questionCount < 5) {
            questionCount++;
            const newQuestion = document.createElement('div');
            newQuestion.classList.add('form-section');
            newQuestion.innerHTML = `
                <label for="question${questionCount}">질문 ${questionCount}</label>
                <input type="text" id="question${questionCount}" name="question${questionCount}" placeholder="질문 ${questionCount} 입력">
            `;
            form.appendChild(newQuestion);
        } else {
            alert('최대 5개까지 추가할 수 있습니다.');
        }
    });

    form.appendChild(addQuestionButton);
});
