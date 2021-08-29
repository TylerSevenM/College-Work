#Tyler Merrett - n01146025
#Reference used for visuals: https://www.youtube.com/watch?v=JtiK0DOeI4A&ab_channel=TechWithTim
#Reference used for A* Algorithm implementation: https://en.wikipedia.org/wiki/A*_search_algorithm
#References to help implementation: https://www.youtube.com/watch?v=Ob9rY6PQMfI&ab_channel=JoeJames

import pygame
import random
import math
import time
from queue import PriorityQueue

red = (255, 0, 0) #searched
green = (0, 255, 0) #final path
blue = (0, 0, 255) #goal
white = (255, 255, 255) #undiscovered cells
black = (0, 0, 0) #BARRIERS
purple = (128, 0, 128) #unexplored neighbors
orange = (255, 165 ,0) #start
grey = (128, 128, 128) #lines dividing cells
onceMain = True
once = True
xbyx = int(input("Type: \"100\", \"200\" or \"300\" for x. Where, grid will become x by x.\n"))
density = int(input("Type: \"10\", \"20\" or \"30\" for Percent Density.\n"))
#selection for building an environment
firstAnswer = input("Type: \"env1\" for Environment 1. Type: \"env2\" for Environment 2.\nType: \"env3\" for ARA* supported Environment.\n")
#selection for algorithm applied
secondAnswer = input("Type: \"alg1\" for AStar Algorithm Implementation 1. Type: \"alg2\" for Budget C AStar Algorithm 2.\nType: \"alg3\" for ARA* Algorithm.\n")
class cell: #what to do when we call/handle cells
	def __init__(self, row, col, width, totalRows):
		self.row = row
		self.col = col
		self.x = row * width
		self.y = col * width
		self.color = white
		self.neighbors = []
		self.width = width
		self.totalRows = totalRows
	def getPosition(self):
		return self.row, self.col
	def closedState(self):
		return self.color == red
	def openState(self):
		return self.color == purple
	def barrier(self):
		return self.color == black
	def startState(self):
		return self.color == orange
	def endState(self):
		return self.color == blue
	def reset(self):
		self.color = white
	def createStart(self):
		self.color = orange
	def createCloser(self):
		self.color = red
	def createOpening(self):
		self.color = purple
	def createBarrier(self):
		self.color = black
	def createEnding(self):
		self.color = blue
	def createPath(self):
		self.color = green
	def draw(self, win):
		pygame.draw.rect(win, self.color, (self.x, self.y, self.width, self.width))
	def updateNeighbors(self, grid):
		self.neighbors = []
		if self.row>0 and not grid[self.row-1][self.col].barrier(): #up
			self.neighbors.append(grid[self.row-1][self.col])
		if self.row<self.totalRows-1 and not grid[self.row+1][self.col].barrier(): #down
			self.neighbors.append(grid[self.row+1][self.col])
		if self.col>0 and not grid[self.row][self.col-1].barrier(): #left
			self.neighbors.append(grid[self.row][self.col-1])
		if self.col<self.totalRows-1 and not grid[self.row][self.col+1].barrier(): #right
			self.neighbors.append(grid[self.row][self.col+1])  
	def __lt__(self, other):
		return False

def h(p1, p2): #heuristic function
	x1, y1 = p1
	x2, y2 = p2
	return abs(x1-x2)+abs(y1-y2)

def finalPath(previous, current, draw): #final path in green
	while current in previous:
		current = previous[current]
		current.createPath()
	draw()	
	print("Algorithm Complete")

def aStarAlgorithm(draw, grid, start, end): #from wikipedia page containing A* algorithm
	count = 0
	pQueue = PriorityQueue()
	pQueue.put((0, count, start))
	previous = {}
	gScore = {spot: float("inf") for row in grid for spot in row}
	gScore[start] = 0
	fScore = {spot: float("inf") for row in grid for spot in row}
	fScore[start] = h(start.getPosition(), end.getPosition())
	hashSet = {start}
	while not pQueue.empty(): 
		for event in pygame.event.get():
			if event.type == pygame.QUIT:
				pygame.quit()
		current = pQueue.get()[2]
		hashSet.remove(current)
		if current == end:
			finalPath(previous, end, draw)
			end.createEnding()
			return True
		for neighbor in current.neighbors:
			tempGScore = gScore[current]+1
			if tempGScore<gScore[neighbor]:
				previous[neighbor] = current #final path to trace back
				gScore[neighbor] = tempGScore #gscore of neighbor
				fScore[neighbor] = tempGScore+h(neighbor.getPosition(), end.getPosition()) #updates euclidean
				if neighbor not in hashSet:
					count += 1
					pQueue.put((fScore[neighbor], count, neighbor))
					hashSet.add(neighbor)
					neighbor.createOpening()
		draw()
		if current != start:
			current.createCloser()
	return False

def BudgetAlgorithm(draw, grid, start, end, constraint): 
	count = 0
	pQueue = PriorityQueue()
	pQueue.put((0, count, start))
	previous = {}
	gScore = {spot: float("inf") for row in grid for spot in row}#video help
	gScore[start] = 0
	fScore = {spot: float("inf") for row in grid for spot in row}
	fScore[start] = h(start.getPosition(), end.getPosition())
	hashSet = {start}
	#needs U function implemented
	while not pQueue.empty(): 
		for event in pygame.event.get():
			if event.type == pygame.QUIT:
				pygame.quit()
		current = pQueue.get()[2]
		hashSet.remove(current)
		if current == end:
			finalPath(previous, end, draw)
			end.createEnding()
			return True
		for neighbor in current.neighbors:
			tempGScore = gScore[current]+1
			if tempGScore<gScore[neighbor]:
				previous[neighbor] = current
				gScore[neighbor] = tempGScore
				fScore[neighbor] = tempGScore+h(neighbor.getPosition(), end.getPosition())


				font = pygame.font.Font('freesansbold.ttf', 12)
				text = font.render("gScore: " + str(gScore[neighbor]), 1, (0, 0, 0))
				disp.blit(text,(800,400))

				font = pygame.font.Font('freesansbold.ttf', 12)
				text = font.render("Constraint: " + str(constraint), 1, (0, 0, 0))
				disp.blit(text,(800,412))
				

				if neighbor not in hashSet and gScore[neighbor] <= constraint:
					count += 1
					pQueue.put((fScore[neighbor], count, neighbor))
					hashSet.add(neighbor)
					neighbor.createOpening()
				if gScore[neighbor] > constraint and pQueue.empty():
					print("Constraint too constraining. Failed to find Goal.")
					break
		draw()
		if current != start:
			current.createCloser()
	return False

def algo3(draw, grid, start, end): 
	W = 10
	changeW = 1
	G = 9999999
	incumbent = start
	OPEN = {start}
	while OPEN:
		if (W == 0):
			#print("stop")
			finalPath(incumbent, end, draw)
			end.createEnding()
			return True
		newSolution, costOfSolution, gScores = algo4(OPEN, W, G, grid, start, end, draw, incumbent)
		if(costOfSolution == False or gScores == False or newSolution == False):
			print("No solution found.")
			return False
		if newSolution is not None:
			G = costOfSolution#extract gScore from newSolution
			incumbent = newSolution
			wFloor = W
		print("W value: ", W)
		W -= changeW
		try:
			for x in gScores:
				if (gScores[x] + h(x, end.getPosition()) >= G):
					del gScores[x]
					OPEN.remove(x)
					#parallel arrangement of path nodes
					#they're looking at the same node but they're storing different information
					#where previous will store the information of the cell
					#and gScores will store the gScore of that corresponding cell, in the same order
					#so if you look at identical indexes they are looking at the same cell
					#where the cell itself exists and the gScore exists in 
					#previous and gScores, respectively.
		except:
			pass
	finalPath(incumbent, end, draw)
	end.createEnding()
	return True

def algo4(OPEN, W, G, grid, start, end, draw, newSolution):
	count = 0
	previous = {}
	gScore = {spot: float("inf") for row in grid for spot in row}
	gScore[start] = 0
	gScorePrevious = {}
	fScore = {spot: float("inf") for row in grid for spot in row}
	fScore[start] = W*h(start.getPosition(), end.getPosition())
	pQueue = PriorityQueue()
	pQueue.put((0, count, start))
	while OPEN:
		current = pQueue.get()[2]
		if(current == end):
			return newSolution, G, gScorePrevious
		try:
			OPEN.remove(current)
		except:
			pass

		for neighbor in current.neighbors:	
			tempGScore = gScore[current]+1		
			if neighbor not in OPEN:
				if tempGScore < gScore[neighbor]:
					count += 1
					fScore[neighbor] = tempGScore+W*h(neighbor.getPosition(), end.getPosition())
					pQueue.put((fScore[neighbor], count, neighbor))
					gScore[neighbor] = gScore[current]+1
					neighbor.createOpening()
					if(gScore[neighbor] + h(neighbor.getPosition(), end.getPosition()) < G):
						previous[neighbor] = current
						gScorePrevious[neighbor] = gScore[neighbor]
						if neighbor == end:
							#returns the Solution, the gScore of Goal node from solution, and
							#gScores of the explicit path taken
							return previous, gScore[neighbor], gScorePrevious#best path

						OPEN.add(neighbor)
			#draw()
			if current != start:
				current.createCloser()
	return False, False, False

def createGrid(rows, width): #the actual cells in data
	grid = []
	gap = width // rows
	for i in range(rows):
		grid.append([])
		for j in range(rows):
			spot = cell(i, j, gap, rows)
			grid[i].append(spot)
	return grid

def drawGrid(win, rows, width): #how we see the cells
	gap = width // rows
	for i in range(rows):
		pygame.draw.line(win, grey, (0, i * gap), (width, i * gap))
		for j in range(rows):
			pygame.draw.line(win, grey, (j * gap, 0), (j * gap, width))

def draw(win, grid, rows, width, environmentPath): #filling environment 
	global once
	global xbyx
	global density
	win.fill(white)
	for row in grid:
		for spot in row:
			spot.draw(win)
	drawGrid(win, rows, width)
	pygame.display.update()
	font = pygame.font.Font('freesansbold.ttf',8)
	
	#environment size:
	pygame.draw.rect(disp, red,(0,0,30,20))
	disp.blit((font.render('100', True, green)), (10,10))

	pygame.draw.rect(disp, green,(0,20,30,20))
	disp.blit((font.render('200', True, red)), (10,30))

	pygame.draw.rect(disp, red,(0,40,30,20))
	disp.blit((font.render('300', True, green)), (10,50))

	#density %:
	pygame.draw.rect(disp, green,(30,0,30,20))
	disp.blit((font.render('10', True, red)), (40,10))

	pygame.draw.rect(disp, red,(30,20,30,20))
	disp.blit((font.render('20', True, green)), (40,30))

	pygame.draw.rect(disp, green,(30,40,30,20))
	disp.blit((font.render('30', True, red)), (40,50))

	#enviroment number:
	pygame.draw.rect(disp, red,(60,0,30,20))
	disp.blit((font.render('env1', True, green)), (70,10))

	pygame.draw.rect(disp, green,(60,20,30,20))
	disp.blit((font.render('env2', True, red)), (70,30))

	pygame.draw.rect(disp, red,(60,40,30,20))
	disp.blit((font.render('env3', True, green)), (70,50))

	#algorithm number:
	pygame.draw.rect(disp, green,(90,0,30,20))
	disp.blit((font.render('alg1', True, red)), (100,10))

	pygame.draw.rect(disp, red,(90,20,30,20))
	disp.blit((font.render('alg2', True, green)), (100,30))

	pygame.draw.rect(disp, green,(90,40,30,20))
	disp.blit((font.render('alg3', True, red)), (100,50))
	
	pygame.display.flip()

	if once:
		once = False
		if environmentPath == 'env1':
			#Triangle1 - Isosceles 
			for i in range(20):
				grid[65+i][65].createBarrier()
			count = 0
			for i in range(40):
				if(i%4==0):
					grid[65+i-count][65-i].createBarrier()
				else:
					count+=1
					grid[65+i-count][65-i].createBarrier()
			count = 0
			for i in range(42):
				if(i%4==0):
					grid[85-i+count][65-i].createBarrier()
				else:
					count+=1
					grid[85-i+count][65-i].createBarrier()	
			#Triangle2 - Scalene
			grid[105][48].createBarrier()
			count = 0
			for i in range(28):
				if(i%4==0):
					grid[105+i-count][47+i].createBarrier()
				else:
					count+=1
				grid[105+i-count][47+i].createBarrier()
			count = 0
			grid[114][55].createBarrier()
			grid[123][63].createBarrier()
			for i in range(24):
				if(i%8==0):
					count+=1
					grid[105+i+count][48+i].createBarrier()
				else:
					grid[105+i+count][48+i].createBarrier()
			count = 0
			for i in range(20):
				if(i%8==0):
					count+=1
					grid[113+i][75-count].createBarrier()
				else:
					grid[113+i][75-count].createBarrier()
			grid[112][75].createBarrier()
			grid[130][70].createBarrier()   
			#Quadrilateral1 -Rectangle
			for i in range(20): #vertical
				grid[100][i+75].createBarrier()
				grid[15][i+75].createBarrier()
			for i in range(86): #horizontal
				grid[i+15][75].createBarrier()
				grid[i+15][95].createBarrier()  
			#Quadrilateral2 - Rectangle
			for i in range(45): #vertical
				grid[125][i+10].createBarrier()
				grid[165][i+10].createBarrier()
			for i in range(41): #horizontal
				grid[i+125][10].createBarrier()
				grid[i+125][55].createBarrier() 
			#Quadrilateral3 - Trapezoid
			for i in range(31):
				grid[85][i+10].createBarrier()
			count = -1
			for i in range(21): 
				if(i%4==0):
					count+=1
				grid[85+i][10-count].createBarrier()
			count = 0
			for i in range(36): #~2.4
				if(i==10 or i==18 or i==26):
					grid[85+i][40-count].createBarrier()
				elif(i%2==0):
					count+=1
				grid[85+i][40-count].createBarrier()
			count = 0
			for i in range(20):
				if(i%4==0):
					count+=1
					grid[105+i-count][5+i].createBarrier()
				else:
					grid[105+i-count][5+i].createBarrier()  
			#Quadrilateral4 - Kite
			for i in range(12):
				grid[170+i][15-i].createBarrier()
			count = 0
			for i in range(16):
				if(i%2==0):
					count+=1
					grid[183+i-count][5+i].createBarrier()
				else:
					grid[183+i-count][5+i].createBarrier()
			count = 0
			for i in range(45):
				if(i%9==0):
					grid[190-count-i][20+i].createBarrier()
				else:
					count-=1
					grid[190-count-i][20+i].createBarrier()
			count = 0
			for i in range(50):
				if(i%3==0):
					count+=1
					grid[169+count][15+i].createBarrier()
				else:
					grid[169+count][15+i].createBarrier()   
			#Pentagon - Uh oh
			count = 0
			for i in range(32): #top left line
				if(i%5==0):
					count+=1
				grid[5+i][29-i+count].createBarrier()
			for i in range(26): #top right line
				grid[60-i][30-i].createBarrier()
			count = 0
			for i in range(31): #bottom right line
				if(i%2==0):
					count+=1
				grid[60-count][30+i].createBarrier()
			count = 0
			for i in range(36): #bottom middle line
				if(i%4==0):
					count+=1
				grid[45-i][60-count].createBarrier()
			grid[10][50].createBarrier()
			count = 0
			for i in range(21): #bottom left line
				if(i%4==0):
					count+=1
				grid[10-count][50-i].createBarrier()    
			#Hexagon - Save the Bees
			for i in range(16):
				grid[180][70+i].createBarrier()
			grid[180][85].createBarrier()
			count = 0
			for i in range(15):
				if(i%3==0):
					count+=1
				grid[165+i][95-i+count].createBarrier()
			count = 0
			for i in range(16):
				if(i%3==0):
					count+=1
				grid[165-i][95-i+count].createBarrier()
			for i in range(14):
				grid[150][85-i].createBarrier()
			count = 0
			for i in range(17): #top left line
				if(i%6==0):
					count+=1
				grid[150+i][71-i+count].createBarrier()
			for i in range(14): #top right line
				grid[167+i][57+i].createBarrier()
		elif environmentPath == 'env2':
			#put text fields here
			#call fScoreWrite("nyah"), 
			#Triangle1 - Isosceles 
			for i in range(20):
				grid[65+i][65].createBarrier()
			count = 0
			for i in range(40):
				if(i%4==0):
					grid[65+i-count][65-i].createBarrier()
				else:
					count+=1
					grid[65+i-count][65-i].createBarrier()
			count = 0
			for i in range(42):
				if(i%4==0):
					grid[85-i+count][65-i].createBarrier()
				else:
					count+=1
					grid[85-i+count][65-i].createBarrier()

			#Parrallelogram
			for i in range(71):
				grid[40+i][94].createBarrier()
				grid[52+i][81].createBarrier()
			for i in range(13):
				grid[39+i][94-i].createBarrier()
				grid[110+i][94-i].createBarrier()
			grid[123][81].createBarrier()

			#Trapezoid
			grid[47][73].createBarrier()
			for i in range(72):
				grid[47][6+i].createBarrier()
			for i in range(43):
				grid[32][21+i].createBarrier()
			for i in range(15):
				grid[33+i][64+i].createBarrier()
				grid[32+i][21-i].createBarrier()

			#Pentagon
			count = 0
			for i in range(11):
				grid[105+i][9+i].createBarrier()
			for i in range(14):
				grid[116][20+i].createBarrier()
			for i in range(9):
				grid[105-i][9+i].createBarrier()
			for i in range(20):
				if(i%2==0):
					count+=1
					grid[97+i][24+count].createBarrier()
				else:
					grid[97+i][24+count].createBarrier()
			grid[97][18].createBarrier()
			grid[97][19].createBarrier()
			grid[97][20].createBarrier()
			grid[97][21].createBarrier()
			grid[97][22].createBarrier()
			grid[97][23].createBarrier()
			grid[97][24].createBarrier()

			#Box
			for i in range(35):
				grid[92][41+i].createBarrier()
				grid[120][41+i].createBarrier()
			for i in range(28):
				grid[92+i][75].createBarrier()
				grid[92+i][41].createBarrier()
		elif environmentPath == 'env3':
			if(xbyx == 100):
				for i in range(int((density/100)*xbyx*xbyx)):
					coordx = random.randrange(0,100)
					coordy = random.randrange(0,100)
					if (coordx != 25 and coordy != 25 or coordx != 75 and coordy != 75):
						grid[coordx][coordy].createBarrier()
				grid[25][25].createStart()
				grid[75][75].createEnding()
			if(xbyx == 200):
				for i in range(int((density/100)*xbyx*xbyx)):
					coordx = random.randrange(0,200)
					coordy = random.randrange(0,200)
					if (coordx != 50 and coordy != 50 or coordx != 150 and coordy != 150):
						grid[coordx][coordy].createBarrier()
				grid[50][50].createStart()
				grid[150][150].createEnding()
			if(xbyx == 300):
				for i in range(int((density/100)*xbyx*xbyx)):
					coordx = random.randrange(0,300)
					coordy = random.randrange(0,300)
					if (coordx != 50 and coordy != 50 or coordx != 250 and coordy != 250):
						grid[coordx][coordy].createBarrier()
				grid[50][50].createStart()
				grid[250][250].createEnding()

#Enables Left Mouse Clicks
#def getClickedPosition(position, ROWS, width):
#	gap = width//ROWS
#	y, x = position
#	row = y//gap
#	col = x//gap
#	return row, col

def main(win, width): #static main predetermining the barriers, start and goal
	global xbyx
	global density
	global once
	global onceMain
	global firstAnswer
	global secondAnswer
	ROWS = xbyx
	grid = createGrid(ROWS, width)
	
	if secondAnswer == "alg2":
		constraint = int(input("Enter a value for constraint on the algorithm: \n"))
	print("Press SPACEBAR on the window to start algorithm. \n")
	if firstAnswer == "env1":
		start = grid[11][85]
		start.createStart()
		###195, 10 ; 11, 80
		end = grid[195][10]
		end.createEnding()
	elif firstAnswer == "env2":
		start = grid[40][85]
		start.createStart()
		end = grid[120][10]
		end.createEnding()
	elif firstAnswer == "env3":
		if (xbyx == 100):
			start = grid[25][25]
			start.createStart()
			end = grid[75][75]
			end.createEnding()
		elif (xbyx == 200):
			start = grid[50][50]
			start.createStart()
			end = grid[150][150]
			end.createEnding()
		elif (xbyx == 300):
			start = grid[50][50]
			start.createStart()
			end = grid[250][250]
			end.createEnding()

	run = True	
	while run:
		draw(win, grid, ROWS, width, firstAnswer)
		
		for event in pygame.event.get():
			if event.type == pygame.QUIT:
				run = False
			if event.type == pygame.KEYDOWN:
				if event.key == pygame.K_SPACE and start and end:
					for row in grid:
						for spot in row:
							spot.updateNeighbors(grid)
					if secondAnswer == 'alg1':
						aStarAlgorithm(lambda: draw(win, grid, ROWS, width, firstAnswer), grid, start, end)
					if secondAnswer == 'alg2':
						BudgetAlgorithm(lambda: draw(win, grid, ROWS, width, firstAnswer), grid, start, end, constraint)
					if secondAnswer == 'alg3':
						startTime = time.time()
						algo3(lambda: draw(win, grid, ROWS, width, firstAnswer), grid, start, end)
						endTime = time.time()
						print(endTime-startTime)
				if event.key == pygame.K_BACKSPACE:
					#xbyx = int(input("Type: \"100\", \"200\" or \"300\" for x. Where, grid will become x by x.\n"))
					#density = int(input("Type: \"10\", \"20\" or \"30\" for Percent Density.\n"))
					once = True

					main(disp, 1200)
			#Allows Left Click to toggle things
			if pygame.mouse.get_pressed()[0]: #left click
				position = pygame.mouse.get_pos()
				#print(position)
				#row, col = getClickedPosition(position, ROWS, width)
				#environment size
				if(0<position[0]<30):
					if 0<position[1]<20:
						xbyx = 100
						print("Environment set to 100x100")
					if 20<position[1]<40:
						xbyx = 200
						print("Environment set to 200x200")
					if 40<position[1]<60:
						xbyx = 300
						print("Environment set to 300x300")
				#density
				if(30<position[0]<60):
					if 0<position[1]<20:
						density = 10
						print("Density % now 10")
					if 20<position[1]<40:
						density = 20
						print("Density % now 20")
					if 40<position[1]<60:
						density = 30
						print("Density % now 30")
				#environment
				if(60<position[0]<90):
					if 0<position[1]<20:
						firstAnswer = 'env1'
						print("Now using Environment 1")
					if 20<position[1]<40:
						firstAnswer = 'env2'
						print("Now using Environment 2")
					if 40<position[1]<60:
						firstAnswer = 'env3'
						print("Now using Environment 3")
				#algorithm
				if(90<position[0]<120):
					if 0<position[1]<20:
						secondAnswer = 'alg1'
						print("Now using Algorithm 1, A*")
					if 20<position[1]<40:
						secondAnswer = 'alg2'
						print("Now using Algorithm 2, A* with Constraint")
					if 40<position[1]<60:
						secondAnswer = 'alg3'
						print("Now using Algorithm 3, ARA*")

	pygame.quit()

disp = pygame.display.set_mode((1200, 1200)) #determining window size
pygame.display.set_caption("A* Path Finding Algorithm") #title at the top of the opened window
pygame.font.init()

#just needs to fix when it fails out, non-iterable

main(disp, 1200) #Racers! Start your engines!