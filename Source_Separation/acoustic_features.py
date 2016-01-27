# This file generates the acoustic features of a given sound file
import pylab
import numpy as np
import wave

sound_path = "/home/aditya/Neural_Net_Git/data_spectrogram/maincat.wav"

if __name__=="__main__":
	wav = wave.open(sound_path, 'r')
	read_frames = wav.readframes(-1)
	frames = np.fromstring(read_frames, 'Int16')
	frame_rate = wav.getframerate()
	# matrix_specgram = pylab.specgram(frames, frame_rate)
	# The first index gives the frequency
	(spect, freqs, time, im) = pylab.specgram(frames, frame_rate)
	# The freqs prints the frequency over the spectrogram
	print('The length of the freqs is {0}'.format(len(freqs)))
	print(freqs)
	print('The length of the spect is {0}'.format(len(spect)))
	print(spect)
	print('The time is of the length {0}'.format(len(time)))
	print(time)
	# print('The time of the im is {0}'.format(len(im)))
	# print(im)
	print('The im object is {0}'.format(im))
	wav.close()
