'''Generates the spectrogram of a wav.file.
	
	A Spectrogram is the representation of a sound, with time in seconds on the x-axis,
	frequency in Hz on the y-axis, 
	In addition a spectrogram also represents intensity (amplitude) of a sound which 
	is represented as a color value on the graph
	'''
import wave
import numpy as np
import matplotlib.pyplot as plt
	
def generate_spectrogram(wav_file):
	# Method to generate the spectrogram
	sound_info, frame_rate = generate_sound_data(wav_file)
	plt.subplot(111)
	plt.title("Spectrogram of %r" %sound)
	plt.xlabel("Time in (s)")
	plt.ylabel("Frequency in Hz")
	plt.specgram(sound_info, Fs=frame_rate)
	plt.savefig("/home/aditya/CCBD_Sound_Internship/Sound_Forensics_ASR_Git/Spectrograms/helloworld_spectrogram.png")
	
def generate_sound_data(wav_file):
	# Method to generate sound data
	wav = wave.open(wav_file, 'rb')
	sound_data = wav.readframes(-1)
	source_data = np.fromstring(sound_data, 'Int16')
	frame_rate = wav.getparams().framerate
	wav.close() # Close the wav file
	return source_data, frame_rate		
		
if __name__=="__main__":
	sound_source = "/home/aditya/Neural_Net_Git/data_spectrogram/" # Sound source path
	sound = "maincat.wav"
	generate_spectrogram(sound_source+""+sound)
	
