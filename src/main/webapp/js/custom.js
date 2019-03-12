function ondeleteclick(formid) {
    swal({
        title: 'Are you sure?',
        icon: "warning",
        dangerMode: true,
        buttons: {
            cancel: true,
            confirm: {
                value: "delete"
            }
        }
    }).then((value) => {
        if(value == "delete"){
        document.getElementById(formid).submit();
        swal(
            'Deleted!',
            'Your file has been deleted.',
            'success'
        );
    }
})
}

function successWindow(title) {
    swal({
        icon: "success",
        title: title,
        timer: 2000,
        buttons: {
            confirm: true
        }
    });
}