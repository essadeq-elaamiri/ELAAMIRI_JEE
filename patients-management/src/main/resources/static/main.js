const deletePatientBtn = document.querySelectorAll(".deletePatientBtn");
deletePatientBtn.forEach(btn => {
    btn.addEventListener('click', event=>{
        event.preventDefault();
        var deleteLink = btn.getAttribute("data-deleteLink");
        console.log(btn);
        if(confirm("Are you sure? want delete ?")){
            location.href = deleteLink;
        }
    })
})