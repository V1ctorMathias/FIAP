document.addEventListener('DOMContentLoaded', () => {
    const buttons = document.querySelectorAll('.toggle-details');

    buttons.forEach(button => {
        button.addEventListener('click', () => {
            const details = button.nextElementSibling;
            if (details.style.display === 'none' || details.style.display === '') {
                details.style.display = 'block';
                button.textContent = 'Ver menos';
            } else {
                details.style.display = 'none';
                button.textContent = 'Ver mais';
            }
        });
    });
});
