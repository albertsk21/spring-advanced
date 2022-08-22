


let loadBtn = document.getElementById("loadBooks");
let form = document.getElementsByTagName("form")[0];
let submitBtn = form.getElementsByTagName("button")[0];

let titleElement = document.getElementById("title");
let authorElement = document.getElementById("author");
let isbnElement = document.getElementById("isbn");



const host = "http://localhost:8080";
let container = document.getElementsByTagName("table")[0]
    .getElementsByTagName("tbody")[0];

loadBtn.addEventListener("click",()=>{

    fetch( host + "/api/books")
    .then(json => json.json())
    .then(data =>{
    
        container.innerHTML = "";

        data.map(item =>{
            let row = document.createElement("tr");
        
            let titleColumn = document.createElement("td");
            titleColumn.textContent = item.title;
        
            let authorColumn = document.createElement("td");
            authorColumn.textContent = item.authorDTO.name;
            let isbnColumn = document.createElement("td");
            isbnColumn.textContent = item.isbn
            let actionColumn = document.createElement("td");
            

            let editBtn = document.createElement("button");
            editBtn.innerHTML = "Edit";

            let deleteBtn = document.createElement("button");
            deleteBtn.innerHTML = "Delete";

            actionColumn.appendChild(editBtn);
            actionColumn.appendChild(deleteBtn);


            row.appendChild(titleColumn);
            row.appendChild(authorColumn);
            row.appendChild(isbnColumn);
            row.appendChild(actionColumn);

            container.appendChild(row);
    


            editBtn.addEventListener("click",() =>{

                titleElement.value = item.title;
                authorElement.value = item.authorDTO.name;
                isbnElement.value = item.isbn;
                form.id = item.id;
            })


            deleteBtn.addEventListener("click",() => {

                let options = {
                    method : "DELETE"
                }

                fetch(host + "/api/books/" + item.id, options);
            })
        });

    })

})





submitBtn.addEventListener("click",(event)=>{
    event.preventDefault();


    let title = titleElement.value;
    let name = authorElement.value;
    let isbn = isbnElement.value;
  

    let options = {
        headers : {}
    }

    let id = form.id;
    console.log(id);


    if( title == "" || name == "" || isbn == ""){
       return alert("Fields cannot be empty");
    }else{
    }

    let data = {
        title,
        isbn,
        authorDTO : {
            name
        }
    }

    options.body = JSON.stringify(data);
    options.headers["Content-Type"] = "application/json";

    if(form.id = ""){
        options.method = "POST";
        fetch( host + "/api/books", options);
    }else{
        options.method = "PUT";
        
        let url =  host + "/api/books/" + id;
   
        fetch( url, options);
    }



});



