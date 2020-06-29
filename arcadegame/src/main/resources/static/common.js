var canvas = document.getElementById("myCanvas");
var ctx = canvas.getContext("2d");
// ボールの大きさ
var ballRadius = 10;
var myMisileX = canvas.width/2;
var myMisileY = canvas.height-30;
var enemyMisileX = -10;
var enemyMisileY = 0;
// 敵のミサイルの速度
var enemyMisileDy = 12;
var enemyMisileExist = false;
var startFlag = true;
var hitStatus = true;
// 自分のミサイルの速度
var myMisileDy = -10;
// パドルの高さ
var paddleHeight = 20;
// パドルの横幅
var paddleWidth = 150;
var paddleX = (canvas.width-paddleWidth)/2;
var mouseClicked = false;
// 敵の一列ごとの数
var brickRowCount = 13;
// 敵の列の数
var brickColumnCount = 3;
// 敵の横幅
var brickWidth = 70;
// 敵の高さ
var brickHeight = 50;
// 敵のパディング
var brickPadding = 30;

var brickOffsetTop = 40;
var brickOffsetLeft = 40;
var score = 0;

var lives = 2;

var item1Flag = true;
var item2Flag = true;
var item3Flag = true;

var item1 = document.getElementById("itemName1");
var item2 = document.getElementById("itemName2");
var item3 = document.getElementById("itemName3");
console.log(item1);
var itemName1 = "1";
var itemName2 = "1";
var itemName3 = "1";
if(item1 !== '<p id="itemName1" class="msg-white item-name1"></p>') {
	itemName1 = item1.textContent;
}
if(item2 !== '<p id="itemName2" class="msg-white item-name2"></p>') {
	itemName2 = item2.textContent;
}
if(item3 !== '<p id="itemName3" class="msg-white item-name3"></p>') {
	itemName3 = item3.textContent;
}
console.log(itemName1);
console.log(itemName2);
console.log(itemName3);

// 効果値取得（タグごと取得）
var Effect1 = document.getElementById("item1-effect");
var Effect2 = document.getElementById("item2-effect");
var Effect3 = document.getElementById("item3-effect");

// 効果値取得（テキストだけ 文字列型）
var itemEffect1 = Effect1.textContent;
var itemEffect2 = Effect2.textContent;
var itemEffect3 = Effect3.textContent;

// 効果値取得（数値型に変換）
var itemnum1 = Number(itemEffect1);
var itemnum2 = Number(itemEffect2);
var itemnum3 = Number(itemEffect3);


document.body.addEventListener('keydown',
    event => {
      if (event.key === 'z' && item1Flag) {
    	  if(itemName1 == 'ライフアップ'){
    		  lives = lives + 1;
    	  }else if(itemName1 == 'スコアアップI') {
    		  score = score * 2;
    	  }else if(itemName1 == 'ハヤクナール') {
    		  myMisileDy = -30;
    		  setTimeout(() => {
    			  myMisileDy = -10;
    		  },5000)
    	  }
        item1Flag = false;
        $('.box1').css('visibility','hidden');
        $('.item-name1').css('visibility','hidden');
      }
    });
document.body.addEventListener('keydown',
    event => {
      if (event.key === 'x' && item2Flag) {
    	  if(itemName2 == 'ライフアップ'){
    		  lives = lives + 1;
    	  }else if(itemName2 == 'スコアアップI') {
    		  score = score * 2;
    	  }else if(itemName2 == 'ハヤクナール') {
    		  myMisileDy = -30;
    		  setTimeout(() => {
    			  myMisileDy = -10;
    		  },5000)
    	  }
        item2Flag = false;
        $('.box2').css('visibility','hidden');
        $('.item-name2').css('visibility','hidden');
      }
    });
document.body.addEventListener('keydown',
    event => {
      if (event.key === 'c' && item3Flag) {
    	  if(itemName3 == 'ライフアップ'){
    		  lives = lives + 1;
    	  }else if(itemName3 == 'スコアアップI') {
    		  score = score * 2;
    	  }else if(itemName3 == 'ハヤクナール') {
    		  myMisileDy = -30;
    		  setTimeout(() => {
    			  myMisileDy = -10;
    		  },5000)
    	  }
        item3Flag = false;
        $('.box3').css('visibility','hidden');
        $('.item-name3').css('visibility','hidden');
      }
    });


var score = 0;
function sample1() {
	document.getElementById("score").value=score;
    document.sampleform.submit();
}


    /* ブロック初期化 */
    var bricks = [];
    for(var c=0; c<brickColumnCount; c++) {
      bricks[c] = [];
      for(var r=0; r<brickRowCount; r++) {
        bricks[c][r] = { x: 0, y: 0, status: 1, misile: 0};
      }
    }

    document.addEventListener("mousemove", mouseMoveHandler, false);
    document.addEventListener("click", mouseClickHandler, false);

    /* 敵ミサイルの状態 */
var enemyMisileStatus = setInterval(function(){
  if(!enemyMisileExist){
        for(var c=0; c<brickColumnCount; c++) {
            for(var r=0; r<brickRowCount; r++) {
                bricks[c][r].misile = 0;
                }
            }
        var randomC = Math.floor( Math.random() * brickColumnCount );
        var randomR = Math.floor( Math.random() * brickRowCount );
        if(bricks[randomC][randomR].status == 1){
            bricks[randomC][randomR].misile = 1;
        }
    }
}, 3000-moveCount*100);

/* 発射ミサイル位置設定 */
var enemyMisilePosition = setInterval(function(){
    if(!enemyMisileExist){
        for(var c=0; c<brickColumnCount; c++) {
            for(var r=0; r<brickRowCount; r++) {
                if(bricks[c][r].misile == 1) {
                    enemyMisileX = bricks[c][r].x+brickWidth/2;
                    enemyMisileY = bricks[c][r].y;
                }
            }
        }
    }
}, 3000-moveCount*100);

var moveCount = 0;
var rightFlag = 1;
/* 敵位置の変更 */
var enemyPosition = setInterval(function(){
    startFlag = false;
    var moveX = 5;
    var moveY = 0;
    if(moveCount>75){
        // 敵が迫ってきたので，ゲームオーバー
        sample1();

    }
    if(moveCount % 10 == 8){
        moveX = 0;
        moveY = 20;
        rightFlag = -rightFlag;
    } else {
        moveX = rightFlag*5;
    }
    for(var c=0; c<brickColumnCount; c++) {
        for(var r=0; r<brickRowCount; r++) {
            bricks[c][r].x += moveX;
            bricks[c][r].y += moveY;
        }
    }
    moveCount++;
}, 1000);

/* パドル位置の取得 */
function mouseMoveHandler(e) {
  var relativeX = e.clientX - canvas.offsetLeft;
  if(relativeX > 0 && relativeX < canvas.width) {
    paddleX = relativeX - paddleWidth/2;
  }
}

/* クリック位置の取得 */
function mouseClickHandler(e) {
  var relativeX = e.clientX - canvas.offsetLeft;
    if(!mouseClicked){
        myMisileX = relativeX;
        mouseClicked = true;
    }
}
var brocknum = 0;
/* ミサイルヒット判定 */
function collisionDetection() {
  for(var c=0; c<brickColumnCount; c++) {
    for(var r=0; r<brickRowCount; r++) {
      var b = bricks[c][r];
      if(b.status == 1) {
        if(myMisileX > b.x && myMisileX < b.x+brickWidth && myMisileY > b.y && myMisileY < b.y+brickHeight) {
          mouseClicked = false;
                    myMisileY = canvas.height-30;
          b.status = 0;
          score += 5 ;
                    console.log("敵にhit");
          brocknum++;
          if(brocknum == brickRowCount*brickColumnCount) {

            $('.score').html(score);
            sample1();
          }
        }
      }
    }
  }
}

/* ミサイルの生成 */
function drawMyMisile() {
  ctx.beginPath();
  ctx.arc(myMisileX, myMisileY, ballRadius, 0, Math.PI*2);
  ctx.fillStyle = "#ff69b4";
  ctx.fill();
  ctx.closePath();
}

/* 敵ミサイルの生成 */
function drawEnemyMisile() {
  ctx.beginPath();
  ctx.arc(enemyMisileX, enemyMisileY, ballRadius, 0, Math.PI*2);
  ctx.fillStyle = "#0095DD";
  ctx.fill();
  ctx.closePath();
}

/* パドルの生成 */
function drawPaddle() {
  ctx.beginPath();
  ctx.rect(paddleX, canvas.height-paddleHeight, paddleWidth, paddleHeight);
  ctx.fillStyle = "#fff";
  ctx.fill();
  ctx.closePath();
}

/* ブロックの生成 */
function drawBricks() {
  for(var c=0; c<brickColumnCount; c++) {
    for(var r=0; r<brickRowCount; r++) {
      if(bricks[c][r].status == 1) {
                if(startFlag){
                    bricks[c][r].x = (r*(brickWidth+brickPadding))+brickOffsetLeft;
            bricks[c][r].y = (c*(brickHeight+brickPadding))+brickOffsetTop;
                }
        ctx.beginPath();
        ctx.rect(bricks[c][r].x, bricks[c][r].y, brickWidth, brickHeight);
        ctx.fillStyle = "#0095DD";
        ctx.fill();
        ctx.closePath();
      }
    }
  }
}

/* スコア表示 */
function drawScore() {
  ctx.font = "30px Arial";
  ctx.fillStyle = "#fff";
  ctx.fillText("Score: "+score, 8, 30);
}

/* HP表示 */
function drawLives() {
  ctx.font = "30px Arial";
  ctx.fillStyle = "#fff";
  ctx.fillText("Life: "+lives, canvas.width-155, 30);
}

/* 描画 */
function draw() {
  ctx.clearRect(0, 0, canvas.width, canvas.height);
  drawBricks();
  drawEnemyMisile();
  drawPaddle();
  drawScore();
  drawLives();
  collisionDetection();

    enemyMisileY += enemyMisileDy + 0.1*moveCount;
    if(enemyMisileY>canvas.height){
        enemyMisileExist = false;
        hitStatus = true;
    } else {
        enemyMisileExist = true;
    }
    if(hitStatus){
        if(canvas.height >= enemyMisileY && enemyMisileY >= canvas.height-ballRadius) {
            if(enemyMisileX > paddleX && enemyMisileX < paddleX + paddleWidth) {
                lives--;
                hitStatus = false;
                if(!lives) {
                	sample1();

                }
            }
        }
    }

    if(mouseClicked){
        drawMyMisile();
        myMisileY += myMisileDy;
        if(myMisileY<=0){
            mouseClicked = false;
            myMisileY = canvas.height-30;
        }
    }
    if(myMisileY > enemyMisileY-ballRadius && enemyMisileY+ballRadius > myMisileY){
        if(myMisileX > enemyMisileX-ballRadius*2 && enemyMisileX+ballRadius*2 > myMisileX){
            console.log("MISILE同士がhit");
            enemyMisileExist = false;
            mouseClicked = false;
            myMisileY = canvas.height-30;
        }
    }
  requestAnimationFrame(draw);
}


draw();
