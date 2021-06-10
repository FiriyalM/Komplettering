var objPeople = [
    {
        username: "sam",
        password: "hej123"
    }
]

function logIn(){
    let screenSize = this.name;  //Knappen som klickas på, mobile/large
    let username;  //Namn på användare
    let password;  //Lösenord till användare
    if(screenSize === "loginform"){
        username = logInForm[0].elements.username.value;  //Hämtar innehåll i användarnamnsfältet när knappen klickas på
        password = logInForm[0].elements.password.value;  //Hämtar innehåll i lösenordsfältet när knappen klickas på     
    
    let hash = bto(username+":"+password);  //Base64 hashning
    let basic = "Basic " + hash;  //Basic Authorization sträng

    fetch("http://localhost:8080/blogg/api/user", {
        method: "GET",
        mode: 'cors',
        headers: {  
            'Authorization': basic,
            'Content-Type': 'text/plain'
        },
        }).then((response) => {
            return response.json();
        }).then(data => {          
            let posts = {};  //Holds posts from data

            for(let i = 0; i < data.length; i++){
                posts[i] = data[i].post;
            }
            
            sessionStorage.setItem("username", username);  //Sparar användare
            sessionStorage.setItem("posts", JSON.stringify(posts));  //Sparar data
            document.getElementById("myForm").submit();
            
        }).catch(err => {
            console.log(err);
    })
    }
}
function publishPost(){
    let screenSize = this.name;  //Vilken knapp som klickades på
    let postContent;  //Texten i inläggen

    if(screenSize === "mobilePost"){  //Om mobil skärm
        postContent = document.getElementById("mTextarea").value;  //Sparar mobil storlekens text
    }else if(screenSize === "largePost"){  //Om stor skärm
        postContent = document.getElementById("lTextarea").value;  //Sparar stor skärm text
    }

    let postData = {  //Objekt med anv och inlägget
        "user": sessionStorage.getItem("username"),
        "post": postContent
    }

    fetch("http://localhost:8080/blogg/api/posts", {
        method: "POST",
        mode: 'cors',
        headers: {  
            'Content-Type': 'text/plain'
        },
        body: JSON.stringify(postData)  //JSON till sträng
        }).then((response) => {
            return response.json();
        }).then(data => {
            let posts = {};  //Håller inläggen

            for(let i = 0; i < data.length; i++){
                posts[i] = data[i].post;  //Sparar inläggen
            }
            
            sessionStorage.setItem("posts", JSON.stringify(posts));  //Sparar inläggen i sessionen
            
            location.replace("Index.html");  //Går till/uppdaterar användarens sida
        }).catch(err => {
            console.log(err);
    })
}

