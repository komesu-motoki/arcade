let canvas = document.getElementById("myCanvas");
let ctx = canvas.getContext("2d");

/********************
 ボールの設定
 ********************/
var BALL_COLOR = 'skyblue'; //ボールの色
var BALL_RADIUS = 10; //ボールの大きさ（半径）
let ballX = canvas.width / 2; //ゲーム開始時のX軸（←→）の位置
let ballY = canvas.height - 30; //ゲーム開始時のY軸（↑↓）の位置
let xMove = 3;  //X方向への移動量
let yMove = 3;  //Y方向への移動量


var score = 0;

/********************
 操作する板の設定
 ********************/
var min = 20;
var max = 301;
var BAR_COLOR = '#FFF'; //板の色
var BAR_HEIGHT = 15;  //板の高さ
var BAR_WIDTH = 60; //板の幅
let barX = (canvas.width - BAR_WIDTH) / 2;
let rigthKeyFlag = false;
let leftKeyFlag = false;
document.addEventListener("mousemove", mouseMoveHandler, false);
/* パドル位置の取得 */
function mouseMoveHandler(e) {
    var relativeX = e.clientX - canvas.offsetLeft;
    if(relativeX > 0 && relativeX < canvas.width) {
        barX = relativeX - BAR_WIDTH/2;
    }
}

/********************
 ブロックの設定
 ********************/
var minWidth = 75;
var maxWidth = 76;
var minMargin = 10;
var maxMargin = 11;
var BLOCK_COLOR = 'orange'
var BLOCK_ROW_COUNT = 6; //ブロックの縦の数
var BLOCK_COLUMN_COUNT = 15; //ブロックの横の数
var BLOCK_WIDTH = Math.floor( Math.random() * (maxWidth + 1 - minWidth) ) + minWidth; //ブロックの幅
var BLOCK_HEGHT = 25; //ブロックの高さ
var BLOCK_MARGIN = Math.floor( Math.random() * (maxMargin + 1 - minMargin) ) + minMargin; //ブロックとブロックの隙間
var BLOCK_AREA_MARGIN = 40; //ブロックを並べる領域と画面の隙間
let blockArray = []; //配置したブロック位置の配列
for(let i = 0; i < BLOCK_ROW_COUNT * BLOCK_COLUMN_COUNT; i++){
  let row = Math.floor(i/BLOCK_COLUMN_COUNT);
  let column = i - (BLOCK_COLUMN_COUNT * row);
  blockArray.push( {
    x : BLOCK_AREA_MARGIN + BLOCK_MARGIN * column + BLOCK_WIDTH * column,
    y : BLOCK_AREA_MARGIN + BLOCK_MARGIN * row +  BLOCK_HEGHT * row
    } );
}

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

function barWidth() {
	BAR_WIDTH = 75;
}
function ballRadius() {
	BALL_RADIUS = 10;
}


document.body.addEventListener('keydown',
    event => {
      if (event.key === 'z' && item1Flag) {
    	  if(itemName1 == 'バー拡大'){
    		  BAR_WIDTH = BAR_WIDTH + Math.floor( Math.random() * (max + 1 - min) ) + min ;
    		  setTimeout(barWidth, 10000)
    	  }else if(itemName1 == 'スコアアップB') {
    		  score = score * 2;
    	  }else if(itemName1 == 'チーサクナール') {
    		  BALL_RADIUS = 4;
    		  setTimeout(ballRadius, 10000)
    	  }else if(itemName1 == 'オーキクナール') {
    		  BALL_RADIUS = 20;
    		  setTimeout(ballRadius, 10000)
    	  }
        item1Flag = false;
        $('.box1').remove();
        $('.item-name1').remove();
      }
    });
document.body.addEventListener('keydown',
    event => {
      if (event.key === 'x' && item2Flag) {
    	  if(itemName2 == 'バー拡大'){
    		  BAR_WIDTH = BAR_WIDTH + Math.floor( Math.random() * (max + 1 - min) ) + min ;
    		  setTimeout(barWidth, 10000)
    	  }else if(itemName2 == 'スコアアップB') {
    		  score = score * 2;
    	  }else if(itemName2 == 'チーサクナール') {
    		  BALL_RADIUS = 4;
    		  setTimeout(ballRadius, 10000)
    	  }else if(itemName2 == 'オーキクナール') {
    		  BALL_RADIUS = 20;
    		  setTimeout(ballRadius, 10000)
    	  }
        item2Flag = false;
        $('.box2').remove();
        $('.item-name2').remove();
      }
    });
document.body.addEventListener('keydown',
    event => {
      if (event.key === 'c' && item3Flag) {
    	  if(itemName3 == 'バー拡大'){
    		  BAR_WIDTH = BAR_WIDTH + Math.floor( Math.random() * (max + 1 - min) ) + min ;
    		  setTimeout(barWidth, 10000)
    	  }else if(itemName3 == 'スコアアップB') {
    		  score = score * 2;
    	  }else if(itemName3 == 'チーサクナール') {
    		  BALL_RADIUS = 4;
    		  setTimeout(ballRadius, 10000)
    	  }else if(itemName3 == 'オーキクナール') {
    		  BALL_RADIUS = 20;
    		  setTimeout(ballRadius, 10000)
    	  }
        item3Flag = false;
        $('.box3').remove();
        $('.item-name3').remove();
      }
    });


function sample2() {
	document.getElementById("score").value=score;
    document.sampleform.submit();
    document.getElementById("test").remove();
}

function drawBall() {
  ballX += xMove;
  ballY += yMove;
  ctx.beginPath();
  ctx.arc(ballX, ballY, BALL_RADIUS, 0, Math.PI * 2);
  ctx.fillStyle = BALL_COLOR;
  ctx.fill();
  ctx.closePath();
  if (ballX + xMove > canvas.width - BALL_RADIUS || ballX + xMove < BALL_RADIUS) {
    xMove = -xMove;
  }
  if (ballY + yMove < BALL_RADIUS) {
    yMove = -yMove;
  } else if (ballY + yMove > canvas.height - BALL_RADIUS) {
    if (ballX > barX && ballX < barX + BAR_WIDTH) {
      yMove = -yMove;
    } else {

    	sample2()
    }
  }
}

function drawBlock() {
  blockArray = blockArray.filter( (block, index) => {
    if (ballX > block.x && ballX < block.x + BLOCK_WIDTH && ballY > block.y && ballY < block.y + BLOCK_HEGHT) {
      yMove = -yMove;
      score = score + 5;
    }else{
      return block;
    }
  });
  if(blockArray.length === 0){

    	sample2()
  }
  blockArray.forEach((block) => {
    ctx.beginPath();
    ctx.rect(block.x, block.y, BLOCK_WIDTH, BLOCK_HEGHT);
    ctx.fillStyle = BLOCK_COLOR;
    ctx.fill();
    ctx.closePath();
  });
}

function drawBar() {
  if (rigthKeyFlag && barX < canvas.width - BAR_WIDTH) {
    barX += 7;
  } else if (leftKeyFlag && barX > 0) {
    barX -= 7;
  }
  ctx.beginPath();
  ctx.rect(barX, canvas.height - BAR_HEIGHT, BAR_WIDTH, BAR_HEIGHT);
  ctx.fillStyle = BAR_COLOR;
  ctx.fill();
  ctx.closePath();
}

function keyDownHandler(e) {
  if (e.key == "Right" || e.key == "ArrowRight") {
    rigthKeyFlag = true;
  } else if (e.key == "Left" || e.key == "ArrowLeft") {
    leftKeyFlag = true;
  }
}

function keyUpHandler(e) {
  if (e.key == "Right" || e.key == "ArrowRight") {
    rigthKeyFlag = false;
  } else if (e.key == "Left" || e.key == "ArrowLeft") {
    leftKeyFlag = false;
  }
}

/* スコア表示 */
function drawScore() {
    ctx.font = "30px Arial";
    ctx.fillStyle = "#fff";
    ctx.fillText("Score: "+score, 8, 30);
}

function draw() {
  ctx.clearRect(0, 0, canvas.width, canvas.height);
  drawBall();
  drawBlock();
  drawBar();
  drawScore();
}


document.addEventListener("keydown", keyDownHandler);
document.addEventListener("keyup", keyUpHandler);
let interval = setInterval(draw, 10);
