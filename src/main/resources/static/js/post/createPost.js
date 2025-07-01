// 배경 이미지 업로드 함수
function setupBackgroundUpload(inputId, bannerId) {
    const photoInput = document.getElementById(inputId);
    const banner = document.getElementById(bannerId);

    if (!photoInput || !banner) {
        console.error(`Element not found: ${inputId} or ${bannerId}`);
        return;
    }

    photoInput.addEventListener('change', function () {
        const file = this.files[0];
        if (file) {
            const reader = new FileReader();
            reader.onload = function (e) {
                banner.style.backgroundImage = `linear-gradient(rgba(0,0,0,0.4), rgba(0,0,0,0.4)), url('${e.target.result}')`;
                banner.style.backgroundSize = 'cover';
                banner.style.backgroundPosition = 'center';
                banner.style.backgroundRepeat = 'no-repeat';
                banner.style.color = 'white';
            };
            reader.readAsDataURL(file);
        }
    });
}

// 사용법 - 각 input과 banner를 연결
setupBackgroundUpload('topPhotoInput', 'topBanner');
setupBackgroundUpload('leftPhotoInput', 'leftBanner');
setupBackgroundUpload('rightPhotoInput', 'rightBanner');