    const showProfileModal  = document.querySelector(".modal")

    function showNav(){
        if(document.getElementById('navFix').classList.contains('sb-sidenav-toggled')){
            document.getElementById('navFix').classList.remove('sb-sidenav-toggled')
        }else{
            document.getElementById('navFix').classList.add('sb-sidenav-toggled')
        }
    }
    function removeStorage(){
        localStorage.removeItem("Lat")
        localStorage.removeItem("Lng")
    }


    function closeProfile(){
        showProfileModal.style.display='none';
    }


    function showProfile(){
        showProfileModal.style.display='block';
    }
