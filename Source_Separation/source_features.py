import wave
import numpy as np
import pylab

dir_path = "/home/aditya/Neural_Net_Git/data_spectrogram"

def specgram_properties(wav_file):
	wav = wave.open(wav_file, 'r')
	read_frames = wav.readframes(-1)
	frames = np.fromstring(read_frames, 'Int16')
	frame_rate = wav.getframerate()
	pylab.figure(num = None, figsize = (19, 12))
	pylab.subplot(111)
	pylab.title('Spectrogram of wave file')
	pylab.xlabel('Time in s')
	pylab.ylabel('Frequency in Hz')
	matrix = pylab.specgram(frames, Fs = frame_rate)
	pylab.savefig('/home/aditya/Neural_Net_Git/testspectrogram.png')
	return matrix


def sound_properties(wav_file):
	wav = wave.open(wav_file, 'r')
	read_frames = wav.readframes(-1)
	frames = np.fromstring(read_frames, 'Int16')
	frame_rate = wav.getframerate()
	n_frames = wav.getnframes()
	time_sample = n_frames / float(frame_rate)
	return frame_rate, n_frames, time_sample, frames.size

if __name__ == "__main__":
	wav_file = dir_path + "/hum_fan_noise.wav"
	frame_rate, n_frames, time_sample, frame_size = sound_properties(wav_file)
	print("Number frames : {0}, Frame_Rate : {1}, time_sample : {2}, frame_size : {3}".format(n_frames, frame_rate, time_sample, frame_size))
	sound_matrix = specgram_properties(wav_file)
	print("Matrix Properties : {0}", sound_matrix)
