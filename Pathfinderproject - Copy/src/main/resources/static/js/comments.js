const routeId = document.getElementById('routeId').value;
const commentCtnr = document.getElementById('commentCtnr');

const allComments = [];

const displayComments = (comments) => {
    commentCtnr.innerHTML = comments.map((c) => asComment(c)).join('');
}

function asComment(c) {
    let commentHTML = `<div id="commentCtnr-${c.id}">`;
    commentHTML += `<h4>${c.author} (${c.created})</h4>`;
    commentHTML += `<p>${c.textContent}</p>`;
    commentHTML += `</div>`;
    return commentHTML;
}

fetch(`http://localhost:8080/api/comments/${routeId}`).
then(response => response.json()).
then(data => {
    for(let comment of data)
    {
        allComments.push(comment);
    }
    displayComments(allComments);
})